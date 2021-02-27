import { IsEmail, Length } from "class-validator";
import { BaseEntity, Column, CreateDateColumn, Entity, PrimaryGeneratedColumn, UpdateDateColumn } from "typeorm";


@Entity('user')
export class User extends BaseEntity {

    @PrimaryGeneratedColumn()
    id: string

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
}