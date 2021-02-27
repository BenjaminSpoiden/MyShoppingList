import { Router } from "express"
import { User } from "../entity/User"

const router = Router()

router.post("/signup", async (req, res) => {
    const {email, password} = req.body

    try {
        const user = await User.create({email, password}).save()
        console.log(user)

        res.status(201).send("User created")
    }catch (e) {
        console.log(e.message)
        res.status(400).send(e)
    }
   
})
router.post("/login", (req, res) => {
    res.send(req.body)
})

export default router



