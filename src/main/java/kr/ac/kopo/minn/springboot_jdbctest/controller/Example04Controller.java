package kr.ac.kopo.minn.springboot_jdbctest.controller;

import kr.ac.kopo.minn.springboot_jdbctest.domain.Member;
import kr.ac.kopo.minn.springboot_jdbctest.repository.MemberRepository04;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Entitmanager Test를 위한 Controller

@Controller
@RequestMapping
public class Example04Controller {
    @Autowired
    MemberRepository04 Repository;

    @GetMapping
    public String viewHompage(Model model) {
        Iterable<Member> memberList = Repository.selectMethod();
        model.addAttribute("memberList", memberList);
        return "viewpage04";
    }

    @GetMapping("/new")
    public String newMethod(Model model){
        model.addAttribute("member", new Member());
        return "viewpage04_new";

    }

    @GetMapping("/insert")
    public String insertMethod(@ModelAttribute("member") Member member){
        Repository.insertMethod(member);
        return "redirect:/exam04";
    }

    @GetMapping("/edit/{id}")
    public String editMethod(Model model, @PathVariable(name = "id") int id){
        Member member = Repository.selectMethodById(id);
        model.addAttribute("member", member);
        return "viewpage04_edit";
    }

    @PostMapping("/update")
    public String updateMethod(@ModelAttribute("member") Member member){
        Repository.updateMethod(member);
        return "redirect:/exam04";
    }

    @GetMapping("/delete/{id}")
    public String deleteMethod(@PathVariable(name = "id") int id){
        Repository.deleteMethodById(id);
        return "redirect:/exam04";
    }
}
