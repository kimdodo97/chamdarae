//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.chamdarae.domain.editor;

import com.chamdarae.domain.Address;
import com.chamdarae.domain.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberEditor {
    private final String name;
    private final Gender gender;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phone;
    private final LocalDate birthDate;
    private final Address address;

    public MemberEditor(String name, Gender gender, String email, String password, String nickname, String phone, LocalDate birthDate, Address address) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    public static MemberEditorBuilder builder() {
        return new MemberEditorBuilder();
    }

    public static class MemberEditorBuilder {
        private String name;
        private Gender gender;
        private String email;
        private String password;
        private String nickname;
        private String phone;
        private LocalDate birthDate;
        private Address address;

        MemberEditorBuilder() {
        }

        public MemberEditorBuilder name(final String name) {
            if(name != null){
                this.name = name;
            }
            return this;
        }

        public MemberEditorBuilder gender(final Gender gender) {
            if(gender!=null){
                this.gender = gender;
            }
            return this;
        }

        public MemberEditorBuilder email(final String email) {
            if(email!=null){
                this.email = email;
            }
            return this;
        }

        public MemberEditorBuilder password(final String password) {
            if(password!=null){
                this.password = password;
            }
            return this;
        }

        public MemberEditorBuilder nickname(final String nickname) {
            if(nickname!=null){
                this.nickname = nickname;
            }
            return this;
        }

        public MemberEditorBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        public MemberEditorBuilder birthDate(final LocalDate birthDate) {
            if(birthDate!=null){
                this.birthDate = birthDate;
            }
            return this;
        }

        public MemberEditorBuilder address(final Address address) {
            if(birthDate!=null){
                this.address = address;
            }
            return this;
        }

        public MemberEditor build() {
            return new MemberEditor(this.name, this.gender, this.email, this.password, this.nickname, this.phone, this.birthDate, this.address);
        }

        public String toString() {
            return "MemberEditor.MemberEditorBuilder(name=" + this.name + ", gender=" + this.gender + ", email=" + this.email + ", loginId=" + this.password + ", nickname=" + this.nickname + ", phone=" + this.phone + ", birthDate=" + this.birthDate + ", birthDate=" + this.birthDate + ")";
        }
    }
}
