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
        nodeList.add(new Node("21","0","总经理",true));
        nodeList.add(new Node("26","0","技术部",true));
        nodeList.add(new Node("27","26","项目经理",false));
        nodeList.add(new Node("28","26","项目组组长",false));
        nodeList.add(new Node("29","0","安全部",true));
        nodeList.add(new Node("30","29","网络安全部负责人",false));
        nodeList.add(new Node("31","29","硬件规格",false));
        nodeList.add(new Node("32","27","后端开发",false));
        nodeList.add(new Node("33","27","前端开发",false));
        return nodeList;
    }



}
