package info.cellardoor.CliniqueSolis.Auth.Controller;

import info.cellardoor.CliniqueSolis.Auth.Http.Request.UserRequest;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.ListUserResponse;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.UserResponse;
import info.cellardoor.CliniqueSolis.Auth.Service.UserService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ListUserResponse> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    ///api/user/search?query=${query}
    @GetMapping("search")
    public ResponseEntity<ListUserResponse> searchUser(
            @RequestParam("query") String query
    ) {
        return ResponseEntity.ok(userService.search(query));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.create(userRequest));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUserById(
            @PathVariable("id") Integer id,
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.updateById(id, userRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(
            @PathVariable("id") Integer id
    ) {
        userService.deleteById(id);
        return ResponseEntity.ok(
                JSONObject.toJSONString(
                        new JSONObject() {{
                            put("message", "User " + id + " deleted successfully");
                        }}
                )
        );
    }
}