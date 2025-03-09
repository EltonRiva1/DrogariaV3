package br.pro.delfino.drogariav3.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.pro.delfino.drogariav3.dao.FabricanteDAO;
import br.pro.delfino.drogariav3.dao.ProdutoDAO;
import br.pro.delfino.drogariav3.domain.Fabricante;
import br.pro.delfino.drogariav3.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	private StreamedContent content;

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			this.produtos = dao.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.produto = new Produto();
			FabricanteDAO dao = new FabricanteDAO();
			this.fabricantes = dao.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (this.produto.getPath() == null) {
				Messages.addGlobalError("O campo foto é obrigatório");
				return;
			}
			ProdutoDAO dao = new ProdutoDAO();
			Produto produto = dao.merge(this.produto);
			Path origin = Paths.get(this.produto.getPath()), destination = Paths.get(
					"C:/Users/notle/Downloads/eclipse-workspace/DrogariaV3/uploads/" + produto.getCodigo() + ".png");
			Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
			this.produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			this.fabricantes = fabricanteDAO.listar();
			this.produtos = dao.listar();
			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException | IOException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um produto");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		try {
			this.produto = (Produto) actionEvent.getComponent().getAttributes().get("produto");
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(this.produto);
			Path path = Paths.get("C:/Users/notle/Downloads/eclipse-workspace/DrogariaV3/uploads/"
					+ this.produto.getCodigo() + ".png");
			Files.deleteIfExists(path);
			this.produtos = dao.listar();
			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException | IOException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			this.produto = (Produto) actionEvent.getComponent().getAttributes().get("produto");
			this.produto.setPath("C:/Users/notle/Downloads/eclipse-workspace/DrogariaV3/uploads/"
					+ this.produto.getCodigo() + ".png");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			this.fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			e.printStackTrace();
		}
	}

	public void upload(FileUploadEvent event) {
		try {
			UploadedFile file = event.getFile();
			Path path = Files.createTempFile(null, null);
			Files.copy(file.getInputstream(), path, StandardCopyOption.REPLACE_EXISTING);
			this.produto.setPath(path.toString());
			Messages.addGlobalInfo("Upload realizado com sucesso");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de arquivo");
			e.printStackTrace();
		}
	}

	public void download(ActionEvent actionEvent) {
		try {
			this.produto = (Produto) actionEvent.getComponent().getAttributes().get("produto");
			InputStream inputStream = new FileInputStream(
					"C:/Users/notle/Downloads/eclipse-workspace/DrogariaV3/uploads/" + this.produto.getCodigo()
							+ ".png");
			this.content = new DefaultStreamedContent(inputStream, "image/png", this.produto.getCodigo() + ".png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Messages.addGlobalInfo("Ocorreu um erro ao tentar efetuar o  download da foto");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
