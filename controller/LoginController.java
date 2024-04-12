@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // Authentication successful
        final String token = jwtUtil.createJwt(loginDTO.getUsername(), "ROLE_USER", 60*60*1000L); // Assuming a default role for now

        return ResponseEntity.ok(new LoginService.LoginResponse(token));
    }

}
