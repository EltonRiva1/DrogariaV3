package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Menu;

public class MenuDAOTest {
	@Test
	public void listar() {
		MenuDAO dao = new MenuDAO();
		List<Menu> menus = dao.listar();
		for (Menu menu : menus) {
			if (menu.getPath() == null) {
				System.out.println(menu.getRotuloMenu() + " - " + menu.getPath());
				for (Menu item : menu.getMenus()) {
					System.out.println("\t" + item.getRotuloMenu() + " - " + item.getPath());
				}
			}
		}
	}
}
