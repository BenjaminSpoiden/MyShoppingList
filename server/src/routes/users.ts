import express from "express"
import { deleteUser, getUser, getUsers, updateUser } from "../controllers/UserController"


const router = express.Router()

router.get("/", getUsers)

router.get("/:id", getUser)

router.delete("/:id", deleteUser)

router.patch("/:id", updateUser)

export default router