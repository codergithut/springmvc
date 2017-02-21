package webSource.rabbit.database.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import rabbit.jpa.entry.DemoInfo;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/20
 * @description
 */
public interface DemoInfoRepository extends CrudRepository<DemoInfo,Long> {

}
