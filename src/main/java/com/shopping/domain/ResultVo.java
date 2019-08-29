package com.shopping.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Name: ResultVo
 * @Description: 页面返回的公用对象
 * @Author cy
 * @Date 2018/5/716:54
 */
public class ResultVo implements Serializable{

    private static final long serialVersionUID = 2980517548391346741L;
    /**
     * 返回结果的编码，例：正常返回：001，异常：返回：001
     */
    private String result_code;
    /**
     * 存放list类型的数据
     */
    private List<Map<String,Object>> list_data;
    /**
     * 存放map类型的数据
     */
    private Map<String,Object> map_data;
    /**
     * 存放json字符串格式的数据
     */
    private String jsonStr;
    /**
     * 返回结果的消息内容，比如错误消息等
     */
    private String result_msg;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public List<Map<String, Object>> getList_data() {
        return list_data;
    }

    public void setList_data(List<Map<String, Object>> list_data) {
        this.list_data = list_data;
    }

    public Map<String, Object> getMap_data() {
        return map_data;
    }

    public void setMap_data(Map<String, Object> map_data) {
        this.map_data = map_data;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "result_code='" + result_code + '\'' +
                ", list_data=" + list_data +
                ", map_data=" + map_data +
                ", jsonStr='" + jsonStr + '\'' +
                ", result_msg='" + result_msg + '\'' +
                '}';
    }
}
