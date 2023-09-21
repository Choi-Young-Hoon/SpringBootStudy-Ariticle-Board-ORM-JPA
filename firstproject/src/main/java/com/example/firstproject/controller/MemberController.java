package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entitiy.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String memberSignUp() {

        return "members/new";
    }

    @PostMapping("/join")
    public String memberJoin(MemberForm form) {
        Member member = form.toEntity();
        this.memberRepository.save(member);
        return "";
    }
}
