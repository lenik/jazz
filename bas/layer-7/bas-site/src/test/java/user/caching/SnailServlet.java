package user.caching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SnailServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Random random = new Random();
    long _ago = 10;
    long ctime;

    public SnailServlet() {
        ctime = System.currentTimeMillis() - _ago * 1000; // 10 seconds before.
    }

    @SuppressWarnings("static-access")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long life = 10; // 10 seconds later.
        resp.setHeader("Cache-Control", "must-revalidate, max-age=" + life);

        String etag = "" + ctime;

        String cachedETag = req.getHeader("If-None-Match");
        if (cachedETag != null)
            if (cachedETag.equals(etag)) {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("ETag", etag);

        PrintWriter out = resp.getWriter();
        for (int i = 0; i < 10; i++) {
            out.print("<div>");
            out.print("Line " + i + ": " + random.nextInt());
            out.println("</div>");
            out.flush();
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                throw new ServletException(e.getMessage(), e);
            }
        }
        out.println("<A href=''>Get this again.</a>");
        out.close();
    }

}
