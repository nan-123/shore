package com.yougou.controller;

import com.yougou.pojo.Node;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/node")
@CrossOrigin
public class NodeController {



    @RequestMapping("/getNodes")
    @ResponseBody
    public List<Node> getNodes() throws Exception{
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(new Node("1","0","硬件规格","false","true"));
        nodeList.add(new Node("10","1","单板","false","true"));
        nodeList.add(new Node("11","1","子卡","false","true"));
        nodeList.add(new Node("12","1","设备","false","true"));
        nodeList.add(new Node("2","0","软件规格","false","true"));
        nodeList.add(new Node("20","2","java","false","true"));
        nodeList.add(new Node("21","2","jscript","false","true"));
        nodeList.add(new Node("22","2","php","false","true"));
        nodeList.add(new Node("100","10","单板_00","false","false"));
        nodeList.add(new Node("101","10","单板_01","false","false"));
        return nodeList;
    }



}
