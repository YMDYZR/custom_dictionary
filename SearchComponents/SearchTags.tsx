import { useState } from 'react';

// propsの型エイリアス
type Props = {
    tag: boolean;
    setTag: React.Dispatch<React.SetStateAction<boolean>>;
};

//チェックされた場合tagの状態をfalseからtureへ、あるいはtrueからfalseへ
const SearchTag = ({ tag, setTag }: Props) => {
    const [checked, setChecked] = useState(false);
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setChecked(e.target.checked);
        setTag(e.target.checked);
    };

    return (
        <div>
            <input type='checkbox' checked={checked} onChange={handleChange} />
        </div>
    )
}
export default SearchTag;

