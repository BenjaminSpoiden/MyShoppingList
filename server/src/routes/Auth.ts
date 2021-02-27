import { Router } from "express"
import { createUser } from "../controllers/AuthControllers"

const router = Router()

router.post("/signup", createUser)
router.post("/login", (req, res) => {
    res.send(req.body)
})

export default router



