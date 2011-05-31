package user;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;

import net.bodz.bas.bean.BeanDump;

public class DynaBeansExampleV1 {

    public static void main(String args[])
            throws Exception {
        Object movie = createMovieBean();
        System.err.println(BeanUtils.getProperty(movie, "title"));
        System.err.println(BeanUtils.getProperty(movie, "director.name"));
        BeanDump.dumpProperties(movie);
    }

    private static Object createMovieBean()
            throws Exception {

        // first create the properties
        DynaProperty properties[] = new DynaProperty[] { new DynaProperty("title", String.class),
                new DynaProperty("dateOfRelease", Date.class), new DynaProperty("keywords", String[].class),
                new DynaProperty("genre", Map.class), new DynaProperty("actors", List.class),
                new DynaProperty("director", DynaBean.class) };

        // next using the properties define the class
        DynaClass movieClass = new BasicDynaClass("movie", null, properties);

        // now, with the class, create a new instance
        DynaBean movieBean = movieClass.newInstance();

        // set its properties
        movieBean.set("title", "The Italian Job");
        movieBean.set("dateOfRelease", new GregorianCalendar(1969, 0, 1).getTime());
        movieBean.set("keywords", new String[] { "Italy", "Bank Robbery" });

        Map<String, String> genre = new HashMap<String, String>();
        genre.put("THR", "Thriller");

        movieBean.set("genre", genre);
        movieBean.set("genre", "ACT", "Action");

        DynaBean director = createPersonBean();
        director.set("name", "Peter Collinson");
        director.set("gender", new Integer(1));

        movieBean.set("director", director);

        return movieBean;
    }

    private static DynaBean createPersonBean() {
        DynaBean person = new LazyDynaBean();
        return person;
    }

}
