import { validate } from "class-validator";
import { User } from "../entity/User";
import { Request, Response} from "express"

export const createUser = async (req: Request, res: Response) => {
    const {email, password} = req.body

    try {
        const user = User.create({email, password})
        const errors = await validate(user)
        if(errors.length > 0) {
            throw new Error("Error")
        }else {
            await user.save()
            console.log(user)
        }
        
        res.status(201).send("User created")
    }catch (e) {
        console.log(e.message)
        res.status(400).send(e)
    }
}