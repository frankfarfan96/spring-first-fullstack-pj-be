package org.farfenix.service;

import lombok.RequiredArgsConstructor;
import org.farfenix.model.Room;
import org.farfenix.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService{
    private final RoomRepository roomRepository;

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomRprice) throws IOException, SQLException {
        Room room = new Room();

        room.setRoomType(roomType);
        room.setRoomPrice(roomRprice);
        if(!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);

            room.setPhoto(photoBlob);
        }

        return roomRepository.save(room);
    }
}
