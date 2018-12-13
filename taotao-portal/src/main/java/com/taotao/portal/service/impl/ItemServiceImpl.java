package com.taotao.portal.service.impl;

import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.entity.TbItemParamItem;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public Item getItemById(Long id) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + id);
        try {
            if (StringUtils.isNotBlank(json)) {
                WebResult result = WebResult.formatToPojo(json, TbItem.class);
                if (result.getStatus() == 200) {
                    TbItem tbItem = (TbItem) result.getData();
                    Item item = new Item(tbItem);
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemDescById(Long id) {
        if (id == null) {
            return null;
        }
        //查询商品描述
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + id);
        //转换成java对象
        WebResult webResult = WebResult.formatToPojo(json, TbItemDesc.class);
        if (webResult.getStatus() == 200) {
            TbItemDesc itemDesc = (TbItemDesc) webResult.getData();
            //取商品描述信息
            String result = itemDesc.getItemDesc();
            return result;
        }
        return null;
    }

    @Override
    public String getItemParamItem(Long id) {
        if (id == null) {
            return null;
        }
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + id);
        WebResult result = WebResult.formatToPojo(json, TbItemParamItem.class);
        if (result.getStatus() == 200 && result.getData() != null) {
            TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
            String paramData = itemParamItem.getParamData();
            List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
            StringBuffer sb = new StringBuffer();
            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
            sb.append("    <tbody>\n");
            for (Map m1 : jsonList) {
                sb.append("        <tr>\n");
                sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
                sb.append("        </tr>\n");
                List<Map> list2 = (List<Map>) m1.get("params");
                for (Map m2 : list2) {
                    sb.append("        <tr>\n");
                    sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                    sb.append("            <td>" + m2.get("v") + "</td>\n");
                    sb.append("        </tr>\n");
                }
            }
            sb.append("    </tbody>\n");
            sb.append("</table>");
            return sb.toString();
        }
        return "";
    }
}