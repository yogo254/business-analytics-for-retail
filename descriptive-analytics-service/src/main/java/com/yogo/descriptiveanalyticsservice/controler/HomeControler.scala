package com.yogo.descriptiveanalyticsservice.controler

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path=Array("/"))
class HomeControler {
  @GetMapping
  def home():String={
    "scala is working "
  }


}
