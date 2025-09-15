package kr.ac.kopo.minn.springboot_jdbctest.controller;

import kr.ac.kopo.minn.springboot_jdbctest.domain.Member;
import kr.ac.kopo.minn.springboot_jdbctest.repository.MemberRepository03;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exam03")
public class Example03Controller {
    @Autowired
    MemberRepository03 repository;

    @GetMapping
    public String viewHomePage(Model model) {
        Iterable<Member> memberList =  repository.selectMethod();
        model.addAttribute("memberList", memberList);
        return "viewPage03";
    }

    @GetMapping("/new")
    public String newMethod(Model model) {
        model.addAttribute("member", new Member());
        return "viewPage03_new";
    }

    @PostMapping("/insert")
    public String insertMethod(@ModelAttribute("member")Member member) {
        repository.insertMethod(member.getName(), member.getAge(), member.getEmail());
        return "redirect:/exam03";
    }

    @GetMapping("edit/{id}")
    public String editMethod(Model model, @PathVariable(name = "id")int id) {
        Member member = repository.selectMethodById(id);
        model.addAttribute("memberList", member);
        return "viewPage03_edit";
    }

    @PostMapping("/upadate")
    public String update(@ModelAttribute("member")Member member) {
        repository.updateMethod(member.getName(), member.getAge(), member.getEmail(), member.getId());
        return "redirect:/exam03";
    }

    @GetMapping("/delete/{id}")
    public String deleteMethod(@PathVariable("id") int id) {
        repository.deleteMethod(id);
        return "redirect:/exam03";
    }

}
