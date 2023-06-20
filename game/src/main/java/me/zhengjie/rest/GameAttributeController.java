/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.domain.GameAttribute;
import me.zhengjie.service.GameAttributeService;
import me.zhengjie.service.dto.GameAttributeQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://eladmin.vip
* @author lyc
* @date 2023-06-12
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "attribute管理")
@RequestMapping("/api/gameAttribute")
public class GameAttributeController {

    private final GameAttributeService gameAttributeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameAttribute:list')")
    public void exportGameAttribute(HttpServletResponse response, GameAttributeQueryCriteria criteria) throws IOException {
        gameAttributeService.download(gameAttributeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询attribute")
    @ApiOperation("查询attribute")
    @PreAuthorize("@el.check('gameAttribute:list')")
    public ResponseEntity<Object> queryGameAttribute(GameAttributeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameAttributeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增attribute")
    @ApiOperation("新增attribute")
    @PreAuthorize("@el.check('gameAttribute:add')")
    public ResponseEntity<Object> createGameAttribute(@Validated @RequestBody GameAttribute resources){
        return new ResponseEntity<>(gameAttributeService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改attribute")
    @ApiOperation("修改attribute")
    @PreAuthorize("@el.check('gameAttribute:edit')")
    public ResponseEntity<Object> updateGameAttribute(@Validated @RequestBody GameAttribute resources){
        gameAttributeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除attribute")
    @ApiOperation("删除attribute")
    @PreAuthorize("@el.check('gameAttribute:del')")
    public ResponseEntity<Object> deleteGameAttribute(@RequestBody Integer[] ids) {
        gameAttributeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}