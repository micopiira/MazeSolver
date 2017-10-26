package me.micopiira.mazesolver;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Nullable
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{MyWebConfig.class};
	}

	@Nullable
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
