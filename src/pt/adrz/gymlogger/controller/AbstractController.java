package pt.adrz.gymlogger.controller;

import javax.servlet.http.HttpServletRequest;

public class AbstractController implements Controller {
	
	private HttpServletRequest request;
    protected String page;

	@Override
	public void execute() {
		
	}

	@Override
	public void init(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getReturnPage() {
		return this.page;
	}

}
