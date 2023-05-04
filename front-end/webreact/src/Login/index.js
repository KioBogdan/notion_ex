import React, { useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [jwt, setJwt] = useLocalState("", "jwt");
    function sendLogReq() {
        const reqBody = {
             email: username,
             password: password,
        };
          
        fetch("/api/auth/login", {
            headers: {
                "Content-Type" : "application/json",
            }, 
            method: "post",
            body: JSON.stringify(reqBody),
            })
              .then((response) => {
                if(response.status === 200)
                    return Promise.all([response.json(), response.headers]);
                else 
                    return Promise.reject("Invalid login attempt");
              })
              .then(([body, headers]) => {
                setJwt(headers.get("authorization")); //async operation; will not see the result just after it finishes
                window.location.href = "dashboard";
              })
              .catch((message) => {
                alert(message);
              });
    }

    return (
        <>
            <div>
                <label htmlFor="email">Email</label>
                <input type="email" 
                       id="email" 
                       value={username} 
                       onChange={(event) => setUsername(event.target.value)}/>
            </div>
            <div>
                <label htmlFor="password">Password</label>
                <input type="password" 
                       id="password" 
                       value={password} 
                       onChange={(event) => setPassword(event.target.value)}/>
            </div>
            <div>
                <button id="submit" 
                        type="button" 
                        onClick={() => sendLogReq()} >
                    Login
                </button>
            </div>
        </>
    );
};

export default Login;