package br.pro.delfino.drogariav3.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean {
	@ManagedProperty("#{param.path}")
	private String path;
	private StreamedContent content;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public StreamedContent getContent() throws IOException {
		if (this.path == null || this.path.isEmpty()) {
			Path path = Paths.get("C:/Users/notle/Downloads/eclipse-workspace/DrogariaV3/uploads/blank.png");
			InputStream inputStream = Files.newInputStream(path);
			this.content = new DefaultStreamedContent(inputStream);
		} else {
			Path path = Paths.get(this.path);
			InputStream inputStream = Files.newInputStream(path);
			this.content = new DefaultStreamedContent(inputStream);
		}
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
