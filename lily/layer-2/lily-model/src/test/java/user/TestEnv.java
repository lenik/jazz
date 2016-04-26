package user;

import javax.sql.DataSource;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IbatisMapperProvider;
import net.bodz.bas.db.jdbc.BoneCPDataSourceProvider;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.db.jdbc.IDataSourceProvider;

public class TestEnv {

    static IMapperProvider mapperProvider;

    static {
        ConnectOptions dsargs = new ConnectOptions();
        dsargs.setPort(1063);
        dsargs.setDatabase("zjhf_db");
        dsargs.setUserName("postgres");
        dsargs.setPassword("cW3EADp8");
        IDataSourceProvider dsp = new BoneCPDataSourceProvider(dsargs);
        DataSource dataSource = dsp.getDataSource();
        mapperProvider = new IbatisMapperProvider(dataSource);
    }

    public static <T extends IMapper> T getMapper(Class<T> mapperClass) {
        return mapperProvider.getMapper(mapperClass);
    }

}
