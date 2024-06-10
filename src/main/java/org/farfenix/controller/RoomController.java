package org.farfenix.controller;

import lombok.RequiredArgsConstructor;
import org.farfenix.model.Room;
import org.farfenix.response.RoomResponse;
import org.farfenix.service.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RequiredArgsConstructor
public class RoomController {
    private final IRoomService roomService;

    public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
                                                   @RequestParam("roomType") String roomType,
                                                   @RequestParam("roomPrice") BigDecimal roomRprice) throws SQLException, IOException {

        Room savedRoom = roomService.addNewRoom(photo, roomType, roomRprice);

        RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(),
                savedRoom.getRoomPrice());

        return ResponseEntity.ok(response);
    }
}
