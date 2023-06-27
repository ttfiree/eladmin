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
import me.zhengjie.domain.GameEquipmentDetail;
import me.zhengjie.service.GameEquipmentDetailService;
import me.zhengjie.service.dto.GameEquipmentDetailQueryCriteria;
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
* @date 2023-06-27
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "equipmentDetail管理")
@RequestMapping("/api/gameEquipmentDetail")
public class GameEquipmentDetailController {

    private final GameEquipmentDetailService gameEquipmentDetailService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameEquipmentDetail:list')")
    public void exportGameEquipmentDetail(HttpServletResponse response, GameEquipmentDetailQueryCriteria criteria) throws IOException {
        gameEquipmentDetailService.download(gameEquipmentDetailService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询equipmentDetail")
    @ApiOperation("查询equipmentDetail")
    @PreAuthorize("@el.check('gameEquipmentDetail:list')")
    public ResponseEntity<Object> queryGameEquipmentDetail(GameEquipmentDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameEquipmentDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增equipmentDetail")
    @ApiOperation("新增equipmentDetail")
    @PreAuthorize("@el.check('gameEquipmentDetail:add')")
    public ResponseEntity<Object> createGameEquipmentDetail(@Validated @RequestBody GameEquipmentDetail resources){
        return new ResponseEntity<>(gameEquipmentDetailService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改equipmentDetail")
    @ApiOperation("修改equipmentDetail")
    @PreAuthorize("@el.check('gameEquipmentDetail:edit')")
    public ResponseEntity<Object> updateGameEquipmentDetail(@Validated @RequestBody GameEquipmentDetail resources){
        gameEquipmentDetailService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除equipmentDetail")
    @ApiOperation("删除equipmentDetail")
    @PreAuthorize("@el.check('gameEquipmentDetail:del')")
    public ResponseEntity<Object> deleteGameEquipmentDetail(@RequestBody Integer[] ids) {
        gameEquipmentDetailService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}