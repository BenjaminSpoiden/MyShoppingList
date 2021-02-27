import express from "express"
import { v4 } from "uuid"

const router = express.Router()

let users: any[] = [
    
]

router.get("/", (req, res) => {
    res.send(users)
})

router.post("/", (req, res) => {
    
    const userId = v4()
    
    const userWithID = {...req.body, id: userId}

    users.push(userWithID)

    res.send("POST Reached")
})

router.get("/:id", (req, res) => {

    const { id } = req.params

    const user = users.find((user) => user.id === id)

    res.send(user)
})

router.delete("/:id", (req, res) => {
    const { id } = req.params

    users = users.filter((user) => user.id !== id)
    res.send("User deleted")
})

router.patch("/:id", (req, res) => {
    const { id } = req.params

    const { firstName, lastName, age } = req.body


    const user = users.find((user) => user.id === id)

    if(firstName) user.firstName = firstName
    if(lastName) user.lastName = lastName
    if(age) user.age = age

    res.send("User updated")

})

export default router