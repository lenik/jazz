package user;

import javax.sql.DataSource;

import net.bodz.bas.db.batis.IMapper;
import net.bodz.bas.db.batis.IMapperProvider;
import net.bodz.bas.db.batis.MybatisMapperProvider;
import net.bodz.bas.db.jdbc.BoneCPDataSourceProvider;
import net.bodz.bas.db.jdbc.DataSourceArguments;
import net.bodz.bas.db.jdbc.IDataSourceProvider;

public class TestEnv {

    static IMapperProvider mapperProvider;

    static {
        DataSourceArguments dsargs = new DataSourceArguments();
        dsargs.setPort(1063);
        dsargs.setDatabase("zjhf_db");
        dsargs.setUserName("postgres");
        dsargs.setPassword("cW3EADp8");
        IDataSourceProvider dsp = new BoneCPDataSourceProvider(dsargs);
        DataSource dataSource = dsp.getDataSource();
        mapperProvider = new MybatisMapperProvider(dataSource);
    }

    public static <T extends IMapper> T getMapper(Class<T> mapperClass) {
        return mapperProvider.getMapper(mapperClass);
    }

}
