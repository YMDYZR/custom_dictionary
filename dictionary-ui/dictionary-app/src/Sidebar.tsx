import React, { useState } from 'react';
import Sidebar from 'react-sidebar';
import MenuIcon from '@mui/icons-material/Menu'; // メニューアイコンをインポート
import CloseIcon from '@mui/icons-material/Close'; // 閉じるアイコンをインポート

const MySidebar = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleSidebar = () => {
        setIsOpen(!isOpen);
    };

    return (
        <Sidebar
            // TODO:サイドバーを開いている状態の時にも閉じるためのアイコンを設置する。
            // 他のページへのリンクを設置する箇所。
            sidebar={
                <div>
                    {/* 閉じるアイコン */}
                    <CloseIcon onClick={toggleSidebar} /><br></br><br></br>
                    {/* 他のページへのリンク */}
                    <b><a href='/search'>検索</a></b><br></br>
                    <b><a href='/'>登録</a></b><br></br>
                    <b><a href='/'>カスタム辞書</a></b><br></br>
                    <b><a href='/'>サイトの使い方</a></b><br></br>
                </div>
            }
            open={isOpen}
            onSetOpen={toggleSidebar}
            styles={{ sidebar: { background: 'white' } }}
        >
            {/* メニューアイコンをクリックするとサイドバーを開く */}
            <MenuIcon onClick={toggleSidebar} />
        </Sidebar>
    );
};

export default MySidebar;
