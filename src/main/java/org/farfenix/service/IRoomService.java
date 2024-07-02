package org.farfenix.service;

import org.farfenix.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomRprice) throws IOException, SQLException;

    List<String> getAllRoomTypes();

    List<Room> getAllRooms();

    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

    void deleteRoom(Long roomId);

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

    Optional<Room> getRoomById(Long roomId);

    List<Room> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate, String roomType);
}
