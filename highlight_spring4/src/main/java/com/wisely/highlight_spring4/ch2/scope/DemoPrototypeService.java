package com.wisely.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 编写 Prototype 的 Bean:
 * 1 声明 Scopt 为 Prototype
 */
@Service
@Scope("prototype")//1
public class DemoPrototypeService {

}
