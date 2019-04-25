package cn.youguang.repository;

import cn.youguang.entity.Cp;
import cn.youguang.entity.Sjrz;
import cn.youguang.entity.Yhhd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface SjrzDao extends JpaRepository<Sjrz, Long> {


    Sjrz findByCpAndYhhdAndSjrq(Cp cp, Yhhd yhhd, Date sjrq);

    Sjrz findByCpAndYhhdAndSjrqAndSjxw(Cp cp, Yhhd yhhd, Date sjrq, String sjxw);
    List<Sjrz> findBySjrqBetween(Date starttime,Date stoptime);
}
