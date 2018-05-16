package cn.edu.jxnu.base.controller.admin.system

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.base.service.IMemorandumService
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.data.domain.Page
import cn.edu.jxnu.base.entity.Memorandum
import cn.edu.jxnu.base.service.specification.SimpleSpecificationBuilder
import cn.edu.jxnu.base.controller.BaseController
import org.apache.commons.lang3.StringUtils
import cn.edu.jxnu.base.service.specification.SpecificationOperator.Operator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.ui.ModelMap
import cn.edu.jxnu.base.common.JsonResult

@Controller
@RequestMapping(value = { Array("/admin/memorandum") })
class MemorandumScala @Autowired() (val memorandumService: IMemorandumService) extends BaseController {

    @RequestMapping(value = { Array("/index") })
    def index() = {
        "admin/memorandum/index";
    }

    @RequestMapping(value = { Array("/list") })
    @ResponseBody
    def list(): Page[Memorandum] = {
        val builder = new SimpleSpecificationBuilder[Memorandum]();
        val searchText = request.getParameter("searchText");
        if (StringUtils.isNotBlank(searchText)) {
            builder.add("userName", Operator.likeAll.name(), searchText);
        }
        val page = memorandumService.findAll(builder.generateSpecification(), getPageRequest());
        println(f"Memorandum：$page.getSize()");
        page;
    }

    @ResponseBody
    @PostMapping(value = { Array("/delete/{id}") })
    def delete(@PathVariable id: Int, map: ModelMap): JsonResult = {
        try {
            memorandumService.delete(id);
            println(f"删除：$id 成功");
        } catch {
            case e: Exception =>
                e.printStackTrace();
                return JsonResult.failure(e.getMessage());
        }
        JsonResult.success();
    }

}