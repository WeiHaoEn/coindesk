package com.example.coindesk.respository;

import com.example.coindesk.CoindeskApplication;
import com.example.coindesk.entity.CoinInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoindeskApplication.class)
@Transactional
public class ICoinRepositoryTest {

    @Autowired
    ICoinRepository repository;

    private static Logger logger = Logger.getLogger(ICoinRepositoryTest.class.getName());

    @Test
    @Rollback(value = false)
    public void findAll(){
        List<CoinInformation> all = repository.findAll();
        for(int i=0;i<all.size();i++){
            System.out.println("id:"+all.get(i).getId()+","+"coinName:"+all.get(i).getCoinName()+","+"code:"+all.get(i).getCode()+","+"rate:"+all.get(i).getRate());
        }

    }

    @Test
    @Rollback(value = false)
    public void edit(){

        CoinInformation coinInformation = new CoinInformation();
        coinInformation.setId(5);
        coinInformation.setCoinName("HKD");
        coinInformation.setCode("HKD");
        coinInformation.setRate("4.52");
        repository.save(coinInformation);
        List<CoinInformation> all = repository.findAll();
        for(int i=0;i<all.size();i++){
            System.out.println("id:"+all.get(i).getId()+","+"coinName:"+all.get(i).getCoinName()+","+"code:"+all.get(i).getCode()+","+"rate:"+all.get(i).getRate());
        }

    }

    @Test
    @Rollback(value = false)
    public void delete(){

        repository.deleteById(6);
        List<CoinInformation> all = repository.findAll();
        for(int i=0;i<all.size();i++){
            System.out.println("id:"+all.get(i).getId()+","+"coinName:"+all.get(i).getCoinName()+","+"code:"+all.get(i).getCode()+","+"rate:"+all.get(i).getRate());
        }

    }

    @Test
    @Rollback(value = false)
    public void add(){

        CoinInformation coinInformation = new CoinInformation();

        coinInformation.setCoinName("SGD");
        coinInformation.setCode("SGD");
        coinInformation.setRate("4.52");
        repository.save(coinInformation);
        List<CoinInformation> all = repository.findAll();
        for(int i=0;i<all.size();i++){
            System.out.println("id:"+all.get(i).getId()+","+"coinName:"+all.get(i).getCoinName()+","+"code:"+all.get(i).getCode()+","+"rate:"+all.get(i).getRate());
        }

    }


}