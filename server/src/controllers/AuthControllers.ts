import { validate } from "class-validator";
import { User } from "../entity/User";
import { Request, Response} from "express"
import jwt from "jsonwebtoken"

const createToken = (id: string) => {
    return jwt.sign({ id }, 'secret cookie placeholder', {
        expiresIn: 365 * 24 * 60 * 60
    })
}

export const createUser = async (req: Request, res: Response) => {
    const {email, password} = req.body

    try {
        const user = User.create({email, password})
        const errors = await validate(user)
        if(errors.length > 0) {
            throw new Error("Error")
        }else {
            await user.save()
            const token = createToken(user.uuid)
            res.cookie("jwt-cookie", token, {httpOnly: true, maxAge: 1000 * 60 * 60 * 24 * 365})
        }
        
        res.status(201).send("User created")
    }catch (e) {
        console.log(e.message)
        res.status(400).send(e)
    }
}

export const loginUser = async (req: Request, res: Response) => {
    const {email, password} = req.body

    
}