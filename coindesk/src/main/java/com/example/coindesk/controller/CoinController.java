package com.example.coindesk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.coindesk.HttpRequest;
import com.example.coindesk.entity.CoinInformation;
import com.example.coindesk.model.BaseResponseModel;
import com.example.coindesk.model.RequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.coindesk.respository.ICoinRepository;


import java.util.*;

@Controller
@ResponseBody
public class CoinController {

    @Autowired
    ICoinRepository repository;

    @GetMapping(value = "/coinInformation")
    public BaseResponseModel coinDesk() {
        String coinInformtaion = HttpRequest.sendRequestGET("https://api.coindesk.com/v1/bpi/currentprice.json");
        TreeMap<String, Object> resultMap = JSON.parseObject(coinInformtaion, new TypeReference<TreeMap<String, Object>>() {
        });

        //
        List<CoinInformation> all = repository.findAll();
        JSONObject bpi = (JSONObject) resultMap.get("bpi");
        HashMap<String, Object> jsonMap = new Gson().fromJson(bpi.toString(), HashMap.class);
        List resultList = new ArrayList();
        Set keySet = jsonMap.keySet();
        for (Object keyName : keySet) {
            Object json = jsonMap.get(keyName);
            Map map = objectToMap(json);
            CoinInformation coinInfo = new CoinInformation();
            coinInfo.setCoinName(coinName(String.valueOf(map.get("code"))));
            coinInfo.setCode(String.valueOf(map.get("code")));
            coinInfo.setRate(String.valueOf(map.get("rate")));
            repository.save(coinInfo);
            resultList.add(coinInfo);
        }

        BaseResponseModel result = new BaseResponseModel();
        result.setResult(resultList);
        result.setResponseCode(200);
        result.setErrorMsg("");
        return result;
    }

    private static Map objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectMapper map = new ObjectMapper();
        Map result = map.convertValue(obj, Map.class);
        return result;
    }

    private static String coinName(String code){

        Map name = new LinkedHashMap();
        name.put("EUR","歐元");
        name.put("USD","美元");
        name.put("GBP","新台幣");

        return name.get(code).toString();
    }

    @GetMapping(value = "/getCoinInformation")
    public BaseResponseModel getCoinInformation() {

        List<CoinInformation> coinInformationList = repository.findAll();
        BaseResponseModel result = new BaseResponseModel();
        result.setResult(coinInformationList);
        result.setResponseCode(200);
        result.setErrorMsg("");
        return result;
    }

    @DeleteMapping(value = "/deleteByCode/{id}")
    public BaseResponseModel deleteCoinInformation(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        List<CoinInformation> coinInformationList = repository.findAll();
        BaseResponseModel result = new BaseResponseModel();
        result.setResult(coinInformationList);
        result.setResponseCode(200);
        result.setErrorMsg("");
        return result;
    }

    @PutMapping(value = "/edit")
    public BaseResponseModel editCoinInformation(@RequestBody CoinInformation model) {

        CoinInformation one = new CoinInformation();
        one.setId(model.getId());
        one.setCode(model.getCode());
        one.setCoinName(model.getCoinName());
        one.setRate(model.getRate());
        repository.save(one);

        List<CoinInformation> all = repository.findAll();
        BaseResponseModel result = new BaseResponseModel();
        result.setResult(all);
        result.setResponseCode(200);
        result.setErrorMsg("");
        return result;
    }

    @PostMapping(value = "/add")
    public BaseResponseModel addCoinInformation(@RequestBody CoinInformation model) {

        CoinInformation one = new CoinInformation();
        one.setCode(model.getCode());
        one.setCoinName(coinName(model.getCoinName()));
        one.setRate(model.getRate());
        repository.save(one);

        List<CoinInformation> all = repository.findAll();
        BaseResponseModel result = new BaseResponseModel();
        result.setResult(all);
        result.setResponseCode(200);
        result.setErrorMsg("");
        return result;
    }
}
