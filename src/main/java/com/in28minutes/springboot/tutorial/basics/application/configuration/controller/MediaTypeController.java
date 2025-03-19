package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.MediaType;

import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IMediaTypeService;

import java.text.SimpleDateFormat;

import java.util.Date;

@Controller
public class MediaTypeController {
    @Autowired
    private IMediaTypeService mediaTypeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/mediaTypes")
    public String listAll(ModelMap model) {
        model.put("mediaTypes", mediaTypeService.getMediaTypes());
        return "mediaTypes";
    }
}
