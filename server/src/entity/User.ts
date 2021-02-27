import { IsEmail, Length } from "class-validator";
import { BaseEntity, BeforeInsert, Column, CreateDateColumn, Entity, Generated, PrimaryGeneratedColumn, UpdateDateColumn } from "typeorm";
import argon2 from "argon2"

@Entity('user')
export class User extends BaseEntity {

    @PrimaryGeneratedColumn()
    id: string

    @Generated("uuid")
    @Column()
    uuid: string

    @Column("text", {unique: true})
    @IsEmail()
    email: string

    @Column("text")
    @Length(8, 16)
    password: string

    @CreateDateColumn()
    created_at: Date

    @UpdateDateColumn()
    updated_at: Date

    @BeforeInsert()
    async onHashPassword() {
        this.password = await argon2.hash(this.password)
    }
}