import { useEffect, useState } from "react"

function useLocalState(defaultValue, key) { // default state, key for which local elemenet to take
    const [value, setValue] = useState(() => {
        const stickyLocalValue = localStorage.getItem(key); //jwt string is the key
        
        return stickyLocalValue !== null ? JSON.parse(stickyLocalValue) : defaultValue;

    }); //no dependencies

    useEffect(() => { //update localstorage when one of the key/value changes
        localStorage.setItem(key, JSON.stringify(value));
    }, [key, value]);

    return [value, setValue];
}

export {useLocalState}