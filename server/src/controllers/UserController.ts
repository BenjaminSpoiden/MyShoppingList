import { Request, Response } from "express"
import { User } from "../entity/User"

export const getUsers = async (_: Request, res: Response) => {
    const users = await User.find()
    res.send(users)
}

export const getUser = async (req: Request, res: Response) => {

    const { id } = req.params

    const user = await User.findOne({ id })
    if(!user) {
        res.status(400).send(`User with id ${id} could not be found.`)
    }else {
        res.status(201).send(user)
    }
}

export const deleteUser = (req: Request, res: Response) => {
    const { id } = req.params
    try {
        User.delete({ id })
        res.status(201).send(`User with id ${id} has been deleted.`)
    }catch(e) {
        res.status(400).send("Something went wrong")
    }
}

export const updateUser = async (req: Request, res: Response) => {
    const { id } = req.params

    const { email } = req.body

    try {
        await User.update({id}, {email})
        res.status(201).send(`User with id ${id} updated`)
    } catch(e) {
        res.status(400).send("Something went wrong")
    }
    
}