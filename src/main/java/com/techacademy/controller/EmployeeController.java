package com.techacademy.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; // 追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        List<Employee> userlist = service.getEmployeeList();
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        model.addAttribute("employeeCount",userlist.size());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }


    //詳細画面表示--------------------------
    @GetMapping("/datail/{id}")
    public String getEmployeedatail(@PathVariable("id") Integer id, Model model) {
        if(id != null) {
            model.addAttribute("employee", service.getEmployee(id));
        }

        // employee詳細画面に遷移
        return "employee/datail";
    }



    /** employee登録画面を表示------------ */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // employee登録画面に遷移
        return "employee/register";
    }

    /** employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(employee);
        }

        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    //--------------------------------

    /** employee更新画面を表示-------------- */
    @GetMapping("/update/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        if(id != null) {
            model.addAttribute("employee", service.getEmployee(id));
        }
        // User更新画面に遷移
        return "employee/update";
    }

    /** employee更新処理 */
    @PostMapping("/update/{id}")
    public String postEmployee(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            return "employee/update";
        }

        service.updateEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    //--------------------------------------

    /** employee削除処理------------------ */
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable(name = "id", required = true) Integer id) {
        Employee employee = service.getEmployee(id);

        service.deleteEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}