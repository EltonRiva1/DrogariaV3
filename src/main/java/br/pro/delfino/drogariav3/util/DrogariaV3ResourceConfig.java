package br.pro.delfino.drogariav3.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class DrogariaV3ResourceConfig extends ResourceConfig {
	public DrogariaV3ResourceConfig() {
		packages("br.pro.delfino.drogariav3.service");
	}
}
