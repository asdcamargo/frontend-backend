package com.frontback.crl.webfilter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = "/rest/*")
public class RESTFilter implements javax.servlet.Filter {

	static Logger logger = LoggerFactory.getLogger(RESTFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
				(HttpServletRequest) request);
		HttpServletRequest httpRequest = (HttpServletRequest) wrappedRequest.getRequest();
		// wrappedRequest.getInputStream().read();

		logger.info("--ID SESSAO: {}--", httpRequest.getSession().getId());
		String uri = httpRequest.getRequestURL().toString();
		logger.info("--IP SOLICITANTE: {}--", httpRequest.getRemoteAddr());
		logger.info("--Invocação de método: {}--", httpRequest.getMethod() + ". URI " + uri);
		if (httpRequest.getMethod().equals("POST")) {
			// String body = IOUtils.toString(wrappedRequest.getReader());
			// wrappedRequest.resetInputStream();
			logger.info("--CONTENT-TYPE: {}--", httpRequest.getContentType());
			// logger.info("--BODY: {}--", body);
		}

		chain.doFilter(wrappedRequest, response);

	}

	private static class ResettableStreamHttpServletRequest extends HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}

		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub

			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
