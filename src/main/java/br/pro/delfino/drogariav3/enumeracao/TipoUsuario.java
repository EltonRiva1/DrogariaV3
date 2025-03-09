package br.pro.delfino.drogariav3.enumeracao;

public enum TipoUsuario {
	ADMINISTRADOR, GERENTE, BALCONISTA;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch (this) {
		case ADMINISTRADOR:
			return "Administrador";
		case GERENTE:
			return "Gerente";
		case BALCONISTA:
			return "Balconista";
		default:
			return null;
		}
	}
}
