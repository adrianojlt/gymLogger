package pt.adrz.gymlogger.controller;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController implements Controller {
	
	private HttpServletRequest request;
    protected String page;

	@Override
	public void execute() { }

	@Override
	public void init(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getPage() {
		return this.page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public HttpServletRequest getRequest() {
		return this.request;
	}

}
