package pt.adrz.gymlogger.restlet.filters;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.data.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.routing.Filter;
import org.restlet.util.Series;

public class CustomHeaders extends Filter {

    public CustomHeaders() {
        super();
    }

    public CustomHeaders(Context context) {
        super(context);
    }

    public CustomHeaders(Context context, Restlet next) {
        super(context, next);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {

        if (Method.OPTIONS.equals(request.getMethod())) {

            @SuppressWarnings("unchecked")
            Series<Header> responseHeaders = (Series<Header>) response.getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);

            if (responseHeaders == null) {
                responseHeaders = new Series<>(Header.class);
                response.getAttributes().put("org.restlet.http.headers", responseHeaders);
            }

            responseHeaders.add("Access-Control-Allow-Origin",		"*");
            responseHeaders.add("Access-Control-Allow-Methods", 	"GET,POST,PUT,DELETE,OPTIONS,HEAD");
            responseHeaders.add("Access-Control-Allow-Headers", 	"Content-Type, Token, Range, Content-Range");
            responseHeaders.add("Access-Control-Request-Headers", 	"Token, Range");
            responseHeaders.add("Access-Control-Expose-Headers", 	"Token, Content-Range");
            responseHeaders.add("Access-Control-Allow-Credentials", "true");
            responseHeaders.add("Access-Control-Max-Age", 			"31536000");

            response.setEntity(new EmptyRepresentation());

            return Filter.SKIP;
        }

        return super.beforeHandle(request, response);
    }

    @Override
    protected void afterHandle(Request request, Response response) {

        if (!Method.OPTIONS.equals(request.getMethod())) {

            @SuppressWarnings("unchecked")
            Series<Header> responseHeaders = (Series<Header>) response.getAttributes().get("org.restlet.http.headers");

            if (responseHeaders == null) {
                responseHeaders = new Series<>(Header.class);
                response.getAttributes().put("org.restlet.http.headers", responseHeaders);
            }

            responseHeaders.add("Access-Control-Allow-Origin", 		"*");
            responseHeaders.add("Access-Control-Allow-Methods", 	"GET,POST,PUT,DELETE,OPTIONS,HEAD");
            responseHeaders.add("Access-Control-Allow-Headers", 	"Content-Type");
            responseHeaders.add("Access-Control-Request-Headers", 	"Range");
            responseHeaders.add("Access-Control-Expose-Headers", 	"Content-Range");
            responseHeaders.add("Access-Control-Allow-Credentials", "true");
            responseHeaders.add("Access-Control-Max-Age", 			"31536000");

            super.afterHandle(request, response);
        }
    }
}
