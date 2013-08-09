<#--
/*
 * $Id: checkboxlist.ftl 1202798 2011-11-16 17:29:03Z jogep $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#assign itemCount = 0/>
<#assign itemDiv = 'div'/>
<#if parameters.list??>
<@s.iterator value="parameters.list">
    <#assign itemCount = itemCount + 1/>
    <#if parameters.listKey??>
        <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
    </#if>
    <#if parameters.listValue??>
        <#assign itemValue = stack.findString(parameters.listValue)?default("")/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
    </#if>
    <#assign itemKeyStr=itemKey.toString() />
<#if parameters.cssStyle?exists>
	<#if "${parameters.cssStyle?html}" == "vertical">
		<div id="${itemDiv}-${parameters.name?html}-${itemCount}"<#rt/>
		<#if tag.contains(parameters.nameValue, itemKey)>
			class="width145 checkBoxDiv checkBoxDivSelect"<#rt/>
		<#else>
			class="width145 checkBoxDiv"<#rt/>
		</#if>
		>
	</#if>
</#if>
<input type="checkbox" name="${parameters.name?html}" value="${itemKeyStr?html}"
       id="${parameters.name?html}-${itemCount}"<#rt/>
    <#if tag.contains(parameters.nameValue, itemKey)>
       checked="checked"<#rt/>
    </#if>
    <#if parameters.disabled?default(false)>
       disabled="disabled"<#rt/>
    </#if>
    <#if parameters.title??>
       title="${parameters.title?html}"<#rt/>
    </#if>
    <#include "/${parameters.templateDir}/simple/css.ftl" />
    <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
    <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
        />
<label for="${parameters.name?html}-${itemCount}" class="checkboxLabel">${itemValue?html}</label>
<#if parameters.cssStyle?exists>
	<#if "${parameters.cssStyle?html}" == "vertical">
		</div>
	</#if>
</#if>
</@s.iterator>
    <#else>
    &nbsp;
</#if>
<input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.name?html}"
       value=""<#rt/>
<#if parameters.disabled?default(false)>
       disabled="disabled"<#rt/>
</#if>
        />