package br.pro.delfino.drogariav3.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.pro.delfino.drogariav3.dao.MenuDAO;
import br.pro.delfino.drogariav3.domain.Menu;

@ManagedBean
@SessionScoped
public class MenuBean {
	private MenuModel menuModel;

	@PostConstruct
	public void iniciar() {
		MenuDAO dao = new MenuDAO();
		List<Menu> menus = dao.listar();
		this.menuModel = new DefaultMenuModel();
		for (Menu menu : menus) {
			if (menu.getPath() == null) {
				DefaultSubMenu defaultSubMenu = new DefaultSubMenu(menu.getRotuloMenu());
				for (Menu item : menu.getMenus()) {
					DefaultMenuItem defaultMenuItem = new DefaultMenuItem(item.getRotuloMenu());
					defaultMenuItem.setUrl(item.getPath());
					defaultSubMenu.addElement(defaultMenuItem);
				}
				this.menuModel.addElement(defaultSubMenu);
			}
		}
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
