/*
 * blanco Framework
 * Copyright (C) 2004-2020 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.vuecomponent;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import blanco.valueobjectts.task.BlancoValueObjectTsProcessImpl;
import blanco.valueobjectts.task.valueobject.BlancoValueObjectTsProcessInput;
import blanco.vuecomponent.task.BlancoVueComponentProcessImpl;
import blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput;

/**
 * Generation test for TypeScript.
 *
 * @author IGA Tosiki
 * @author tueda
 */
public class BlancoVueComponentTest {

    @Test
    public void testBlancoVueComponent() {
        /* First, creates a ValueObject. */
        BlancoValueObjectTsProcessInput input = new BlancoValueObjectTsProcessInput();
        input.setMetadir("meta/objects");
        input.setEncoding("UTF-8");
        input.setSheetType("php");
        input.setTmpdir("tmpObjects");
        input.setTargetdir("sample/blanco");
        input.setTargetStyle("maven");
        input.setTabs(2);
        input.setVerbose(true);
        input.setLineSeparator("LF");

        BlancoValueObjectTsProcessImpl imple = new BlancoValueObjectTsProcessImpl();
        try {
            imple.execute(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Next, creates a VueComponent. */
        BlancoVueComponentProcessInput inputComponent = new BlancoVueComponentProcessInput();
        inputComponent.setMetadir("meta/components");
        inputComponent.setEncoding("UTF-8");
        inputComponent.setSheetType("php");
        inputComponent.setTmpdir("tmpTest");
        inputComponent.setSearchTmpdir("tmpObjects");
        inputComponent.setTargetdir("sample/blanco/main/vuecomponent");
        inputComponent.setTargetStyle("free");
        inputComponent.setTabs(2);
        inputComponent.setVerbose(true);
        inputComponent.setListClass("sample.pages.RoutePath");
        inputComponent.setRouteRecordMap("sample.pages.RouteRecordMap");
        inputComponent.setRouteRecordMapKey("name");
        inputComponent.setRouteRecordBreadCrumbName("alias");
        inputComponent.setBreadCrumbInterface("sample.pages.BreadCrumbInterface");
        inputComponent.setMenuItemInterface("sample.pages.MenuItemInterface");
        inputComponent.setMenuItemDescription("subject");
        inputComponent.setPermissionKindMap("sample.pages.PermissionKindMap");
        inputComponent.setLineSeparator("LF");
        inputComponent.setStrictNullable(true);
        inputComponent.setSupportedVueVersion("3.5");

        BlancoVueComponentProcessImpl imple2 = new BlancoVueComponentProcessImpl();
        try {
            imple2.execute(inputComponent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
