
package cn.youguang.service;

import cn.youguang.entity.Hy;
import cn.youguang.entity.Zt;
import cn.youguang.repository.HyDao;
import cn.youguang.repository.ZtDao;
import cn.youguang.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//Spring Bean的标识.
@Service
@Transactional
public class ZtService {


    @Autowired
    private HyDao hyDao;


    @Autowired
    private ZtDao ztDao;


    /**
     * 保存/更新行业
     *
     * @param zt
     */
    public void save(Zt zt) {
        ztDao.save(zt);
    }


    public Zt findById(Long id) {


        return ztDao.findOne(id);
    }


    public void findDataTables(PageInfo pageInfo) {

        Page<Zt> zts;

        String ztmc = (String) pageInfo.getCondition().get("ztmc");


        if (StringUtils.isNoneBlank(ztmc)) {
            zts = ztDao.findByZtmcLike(ztmc, pageInfo.getPagerequest());
        } else {
            zts = ztDao.findAll(pageInfo.getPagerequest());
        }

        pageInfo.setRows(zts.getContent());
        pageInfo.setTotal(zts.getTotalElements());

    }

    public List<Zt> findList(Map<String, Object> condition) {
        String ztmc = (String) condition.get("ztmc");
        if (StringUtils.isNoneBlank(ztmc)) {
            return ztDao.findByZtmcLike(ztmc);
        } else {
            return ztDao.findAll();
        }


    }

    public void deleteById(Long id) {
        ztDao.delete(id);
    }

    public List<Zt> findByIds(Long[] zts) {
        return ztDao.findByIdIn(Arrays.asList(zts));
    }
}
