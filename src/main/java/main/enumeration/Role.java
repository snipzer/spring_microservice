package main.enumeration;

public enum Role {

    USER(1L), ADMIN(2L);

    private Long id;

    Role(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Role getRoleById(Long id) {
        for(Role role : Role.values()) {
            if(role.id.equals(id)) {
                return role;
            }
        }
        return null;
    }
}
