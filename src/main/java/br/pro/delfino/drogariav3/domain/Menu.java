package br.pro.delfino.drogariav3.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity
public class Menu extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String rotuloMenu;
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(referencedColumnName = "codigo")
	private List<Menu> menus;
	@Column(length = 50)
	private String path;

	public String getRotuloMenu() {
		return rotuloMenu;
	}

	public void setRotuloMenu(String rotuloMenu) {
		this.rotuloMenu = rotuloMenu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
